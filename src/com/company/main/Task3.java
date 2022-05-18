package com.company.main;

public class Task3 implements Runnable{
    int count;
    Thread thread;
    static boolean stop = false;
    static String currentName;

    Task3(String name){
        thread = new Thread(this, name);
        count = 0;
        currentName = name;
    }
    public void run(){
        System.out.println(thread.getName() + "starts to operate");
        do{
            count++;
            if(currentName.compareTo(thread.getName()) != 0){
                currentName = thread.getName();
                System.out.println(currentName + "is executed");
            }
        }
        while (stop == false && count < 10_000_000);
        stop = true;
        System.out.println("\n" + thread.getName() + "finishes running");
    }
}

class Priority{
    public static void main(String[] args){
        Task3 pr1 = new Task3("High priority thread");
        Task3 pr2 = new Task3("Thread with low priority");
        Task3 pr3 = new Task3("Thread #1 with normal priority");
        Task3 pr4 = new Task3("Thread #2 with normal priority");
        Task3 pr5 = new Task3("Thread #3 with normal priority");

        pr1.thread.setPriority(Thread.MAX_PRIORITY);
        pr2.thread.setPriority(Thread.MIN_PRIORITY);
        pr3.thread.setPriority(Thread.NORM_PRIORITY);
        pr4.thread.setPriority(Thread.NORM_PRIORITY);
        pr5.thread.setPriority(Thread.NORM_PRIORITY);

        pr1.thread.start();
        pr2.thread.start();
        pr3.thread.start();
        pr4.thread.start();
        pr5.thread.start();

        try{
            pr1.thread.join();
            pr2.thread.join();
            pr3.thread.join();
            pr4.thread.join();
            pr5.thread.join();
        }
        catch(InterruptedException ex){
            System.out.println("The main thread starts running");
            System.out.println("\n The High priority thread counted to" + pr1.count);
            System.out.println("The low priority thread counted to" + pr2.count);
            System.out.println("#1 Normal priority thread counted to" + pr3.count);
            System.out.println("#2 Normal priority thread counted to" + pr4.count);
            System.out.println("#3 Normal priority thread counted to" + pr5.count);
        }
    }
}
