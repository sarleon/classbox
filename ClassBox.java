package com.company;
import java.io.*;
import java.util.Scanner;
public class ClassBox {
    String date;
    String classnum;
    String classname;
    String classroom;
     static ClassBox[] storage=new ClassBox[40];
    static int classfinalnum = 0;
     static void toCommand(String input) {
        switch (input.charAt(0)){
            case 'A': String[] tem1 = input.split(";");
                String[] tem2 = tem1[0].split(" ");
                String _date = tem2[1];
                String _classnum = tem1[1];
                String _classname = tem1[2];
                String _classroom = tem1[3];
                add(_date,_classnum,_classname,_classroom);break;
            case 'R': tem1 = input.split(";");
                tem2 = tem1[0].split(" ");
                _date = tem2[1];
                _classnum = tem1[1];
                remove(_date,_classnum);break;
            case 'S':print();break;
            case 'F':find(input);break;
            case 'U':tem1 = input.split(";");
                tem2 = tem1[0].split(" ");
                _date = tem2[1];
                _classnum = tem1[1];
                _classname = tem1[2];
                _classroom = tem1[3];
                update(_date,_classnum,_classname,_classroom);break;
            case 'E':save();break;
            default:System.out.println("COMMAND WRONG");
        }
    }
      static void add(String _date, String _classnum, String _classname, String _classroom){
          for (int x = 0; x < classfinalnum; x++) {
              if (storage[x].date.equals(_date) && storage[x].classnum.equals(_classnum)) {
                  System.out.println("Class time conflict,class adding failed,please enter other command");
                  return;
              }
          }

        ClassBox classbox=new ClassBox();
        classbox.date = _date;
        classbox.classnum = _classnum;
        classbox.classname = _classname;
        classbox.classroom = _classroom;
        storage[classfinalnum]=classbox;
          classfinalnum++;
          System.out.println("Add class complete");
    }
     static void remove(String _date, String _classnum) {
            for(int x=0;x<classfinalnum;x++) {
                if(storage[x].date.equals(_date)&&storage[x].classnum.equals(_classnum))
                storage[x]=null;
            }
         System.out.println("Remove class complete");
    }
    public static void print(){
        System.out.println("Class table:");
        for(int x=0;x<classfinalnum;x++){
            if(storage[x]!=null)
            System.out.println(storage[x].date+storage[x].classnum+storage[x].classname+storage[x].classroom);
        }
    }
    public static void find(String tem1){
        String[] tem2=tem1.split(" ");
        String[] tem3=tem2[1].split(";");
        for(int x=0;x<classfinalnum;x++){
            if(storage[x].date.equals(tem3[0])&&storage[x].classnum.equals(tem3[1]))
                System.out.println(storage[x].date+storage[x].classnum+storage[x].classname+storage[x].classroom);
        }
    }
    public static void update(String tem1,String tem2, String tem3, String tem4) {
        for (int x = 0; x < classfinalnum; x++) {
            if (storage[x].date.equals(tem1)&& storage[x].classnum.equals(tem2)) {
                storage[x].classname=tem3;
                storage[x].classroom=tem4;
            }
        }
        System.out.println("Update complete");
    }
    static void save(){
                try {
                    FileWriter fw = new FileWriter("classbox.txt");
                    for(int x=0;x<classfinalnum;x++){
                    if (storage[x] != null) {
                        fw.write(storage[x].date + storage[x].classnum + storage[x].classname + storage[x].classroom+"\n");
                        }
                    }
                    fw.flush();
                    fw.close();
                }catch (IOException e){
                }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("########################CLASS BOX v1.0##############################");
        System.out.println("Enter your command (use the upper form)");
        String tempstr;
       do {
           tempstr = scanner.nextLine();
           toCommand(tempstr);
       }while (!tempstr.equals("EXIT"));
        System.out.println("Class information had been saved into classbox.txt,thank you for using this");
    }
}
