package edu.umb.cs681.hw07;


public class Main {

    public static void main(String[] args) {
        System.out.printf("*************CS681 Homework 07*************\n");

        File file = new File("HW07");

        Editor editor = new Editor(file);
        AutoSaver autoSaver = new AutoSaver(file);;


        Thread editorThread = new Thread(editor);
        Thread autoSaverThread = new Thread(autoSaver);


        editorThread.start();
        autoSaverThread.start();

        try {
            Thread.sleep(1000);
            editor.setDone();
            autoSaver.setDone();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
