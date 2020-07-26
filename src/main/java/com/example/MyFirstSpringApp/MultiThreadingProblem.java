package com.example.MyFirstSpringApp;

public class MultiThreadingProblem {

    public static void main(String[] args) {
        int n = 555555;
        System.out.println("Value of n is: " + n);

        // Non MultiThreading solution
        long startTime = System.currentTimeMillis();
        computeNonMT(n);
        System.out.println("Non MT - Total time taken: " + (System.currentTimeMillis() - startTime));

        // MultiThreading Solution
        startTime = System.currentTimeMillis();

        long leftOutOddNum = 0;
        long finalResult = 0;

        if (n % 2 != 0) {
            leftOutOddNum = n;
            n = n - 1;
        }

        MyThread[] threadsArr = new MyThread[n / 2];

        for (int i = 0; i < threadsArr.length; i++) {
            threadsArr[i] = new MyThread(n);
            threadsArr[i].start();
            n = n - 2;
        }

        for (int i = 0; i < threadsArr.length; i++) {
            try {
                threadsArr[i].join();
                finalResult += threadsArr[i].result;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (leftOutOddNum != 0)
            finalResult = finalResult - (leftOutOddNum * leftOutOddNum);

        System.out.println("finalResult is " + finalResult);
        System.out.println("MT - Total time taken: " + (System.currentTimeMillis() - startTime));

    }

    private static class MyThread extends Thread {

        long result;
        long currNum;

        public MyThread(int num) {
            currNum = num;
            result = 0;
        }

        @Override
        public void run() {
            compute();
        }

        public void compute() {
            // As a^2 - b^2 = (a+b)*(a-b) i.e. (2*a-1) where a and b are consecutive numbers
            this.result = 2 * this.currNum - 1;

        }
    }

    public static void computeNonMT(int n) {

        long finalResult = 0;

        for (long i = n; i > 0; i--) {

            if (i % 2 == 0) {
                finalResult += i * i;
            } else {
                finalResult -= i * i;
            }
        }
        System.out.println("finalResult: " + finalResult);
    }
}
