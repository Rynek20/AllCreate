package allcreate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer extends Thread {

    private int waitTime;
    private boolean active;
    private ArrayList<Action> actionsList;

    public Timer() {
        this.waitTime = 500;
        actionsList = new ArrayList<>();
    }

    public Timer(int waitTime) {
        this.waitTime = waitTime;
        actionsList = new ArrayList<>();
    }
    
    public synchronized void activate(){
        active = true;
        this.start();
    }

    public synchronized boolean activateMethod(Object object, String methodName) {
        try {
            Method method = object.getClass().getMethod(methodName);
            Action action = new Action();
            action.setObject(object);
            action.setMethodName(methodName);
            actionsList.add(action);
            return true;
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public synchronized void run() {
        while (active) {
            try {
                this.wait(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
            actionsList.stream().forEach((_item) -> {
                try {
                    Method method = _item.getObject().getClass().getMethod(_item.getMethodName());
                    method.invoke(_item.getObject());
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    private class Action {

        private Object object;
        private String methodName;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }
}
