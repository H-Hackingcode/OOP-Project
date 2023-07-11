import java.util.Scanner;
public class Process {

    String name="P";
    int burstTime;
    int  arrivalTime;
    double priority;
    double startTime;
    double endTime;
    double waitingTime;
    double turnAroundTime;
    double responseTime;
    static int totalProcess=0;
    static int totalBurstTime=0;
    static double totalTurnAroundTime=0;
    public static double totalWaitingTime;
    //Primitive Processes Options
    public boolean responseTimeCheck=false;
    public boolean startTimeCheck=false;


    public Process(String priority) {

        name+=totalProcess+1;
        totalProcess++;
        Scanner keyboard=new Scanner(System.in);
        System.out.print("\nEnter the Burst time of this Process :");
        this.burstTime = keyboard.nextInt();
        System.out.print("Enter the Arrival time of this Process :");
        this.arrivalTime = keyboard.nextInt();
        System.out.print("Enter the Priority of this Process :");
        this.priority = keyboard.nextDouble();
        totalBurstTime+=this.burstTime;
    }

    public Process() {
        name+=totalProcess+1;
        totalProcess++;
        Scanner keyboard=new Scanner(System.in);
        System.out.print("\nEnter the Burst time of this Process :");
        this.burstTime = keyboard.nextInt();
        System.out.print("Enter the Arrival time of this Process :");
        this.arrivalTime = keyboard.nextInt();
        totalBurstTime+=this.burstTime;
    }

}
