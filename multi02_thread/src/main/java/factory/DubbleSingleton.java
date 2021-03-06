package factory;

/**
 * Created by lewis on 2017/9/7.
 *  单例模式的线程安全写法2 (懒汉模式)：
 *      通过2次检查，保证 单例模式 的线程安全
 */
public class DubbleSingleton {

    private static DubbleSingleton ds;

    public static DubbleSingleton getDs(){
        if(ds==null){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DubbleSingleton.class){
                if (ds==null){
                    ds = new DubbleSingleton();
                }
            }
        }
        return ds;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getDs().hashCode());
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getDs().hashCode());
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getDs().hashCode());
            }
        },"t3");

        t1.start();
        t2.start();
        t3.start();
    }

}
