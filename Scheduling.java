import java.util.*;

public class Scheduling {

    public static void main(String[] args)
    {
        Scanner keyboard=new Scanner(System.in);
        ArrayList<Process> list=new ArrayList<>();

        int choiceOfMenu;
        System.out.println("**********Menu**********");
        System.out.println("1.Firs Come First Serve(FCFS)");
        System.out.println("2.Primitive Short Jump First(PSJF)\n3.NonPrimitive Short Jump First(NPSJF)");
        System.out.println("4.Primitive Priority(PP)\n5.NonPrimitive Priority(NPP)");
        System.out.println("6.Round Robin (RR)");
        System.out.println("7.Banker Algorithm");
        System.out.print("Enter the choice number :");
        choiceOfMenu=keyboard.nextInt();

        if(choiceOfMenu>7)
        {
            System.out.println("You enter the wrong number\n");
            return;
        }
        System.out.print("Enter the number of Processes you want :");
        int numberOfProcesses=keyboard.nextInt();
        if(choiceOfMenu==7)
        {
            Main.mainBanker();
            return;
        }

        else if((choiceOfMenu==4)||(choiceOfMenu==5))
        {
            for(int i=1 ; i<=numberOfProcesses ; i++)
            {
                Process process=new Process("Priority");
                list.add(process);
            }
        }
        else
        {
            for(int i=1 ; i<=numberOfProcesses ; i++)
            {
                Process process=new Process();
                list.add(process);
            }
        }

        switch (choiceOfMenu) {
            case 1 -> list=fCFS(list);
            case 2 -> list=primitiveSJF(list);
            case 3 -> list=nonPrimitiveSJF(list);
            case 4 -> list=primitivePriority(list);
            case 5 -> list=nonPrimitivePriority(list);
            case 6 -> {System.out.print("Enter the time quantum for Round Robin algorithm: ");
                        int timeQuantum = keyboard.nextInt();
                         list = roundRobin(list, timeQuantum);}
        }
        System.out.println("");
        //Print the result

        System.out.format("%-10s  %-10s  %-10s  %-10s  %-10s  %-10s  %-10s","Process","Batch Time","Arrival Time","Priority","Waiting Time","Turnaround Time","Response Time"+"\n");
        for(Process i :list)
        {
            System.out.format("%-10s  %-10s  %-12s  %-10s  %-12s  %-15s  %-10s",i.name,i.burstTime,i.arrivalTime,i.priority,i.waitingTime,i.turnAroundTime,i.responseTime);
            System.out.println("");
        }

        System.out.println("\nThroughput Time ="+(Process.totalBurstTime/Process.totalProcess));
        System.out.println("Average Waiting Time ="+Process.totalWaitingTime/Process.totalProcess);
        System.out.println("Average Turnaround Time ="+Process.totalTurnAroundTime/Process.totalProcess);


    }

    public static ArrayList<Process> fCFS(ArrayList<Process> list)
    {
        Sort.sortArrayList(list,0);//Sorting for first come ont bases of Arrival Time

        for(int i=0 ; i<list.size(); i ++)
        {
            int totalTime=0;
            for(int j=0 ; j<i ;j++)
            {
                totalTime+=list.get(j).burstTime;
            }
            list.get(i).startTime=totalTime;
        }

        //other than first Process set waiting time for all Processes
        for (Process process : list) {
            process.waitingTime = process.startTime - process.arrivalTime;
            process.turnAroundTime = process.burstTime + process.waitingTime;
            process.responseTime = process.waitingTime;
            Process.totalTurnAroundTime += process.turnAroundTime;
            Process.totalWaitingTime += process.waitingTime;
        }
        return list;
    }
    public static ArrayList<Process> primitiveSJF(ArrayList<Process> givenList)
    {
        Map<String ,Integer> list2=new HashMap<>();

        for(Process p:givenList)
        {
            list2.put(p.name,p.burstTime);
        }
        int timeLine=0;
        ArrayList<Process> list=new ArrayList<>(givenList);
        ArrayList<Process> listNew=new ArrayList<>();

        Sort.sortArrayList(list,0);

        ArrayList<Process> listNewTwo = new ArrayList<>();
        while(timeLine!=Process.totalBurstTime) {
            for (Process process : list) {
                if (process.arrivalTime <= timeLine) {
                    listNew.add(process);
                } else
                    break;
            }


            Sort.sortArrayList(listNew,1);

            final int  i=0;

            if(!listNew.get(i).startTimeCheck)
            {
                listNew.get(i).startTime=timeLine;
                listNew.get(i).startTimeCheck=true;
            }

            timeLine++;
            listNew.get(i).burstTime-=1;

            for(int j=1 ; j<listNew.size() ; j++)
            {
                listNew.get(j).waitingTime++;
                Process.totalWaitingTime++;
            }
            if(listNew.get(i).burstTime==0) {
                listNew.get(i).endTime=timeLine;
                listNew.get(i).responseTime=listNew.get(i).startTime-listNew.get(i).arrivalTime;
                listNew.get(i).turnAroundTime=listNew.get(i).endTime-listNew.get(i).arrivalTime;
                Process.totalTurnAroundTime+=listNew.get(i).turnAroundTime;
                list.remove(listNew.get(i));
            }
            else{
                int indexOfNewList=list.indexOf(listNew.get(i));
                list.set(indexOfNewList,listNew.get(i));
            }
            Process newProcess=listNew.get(i);
            listNewTwo.add(newProcess);

                listNew.clear();

        }

        for(Process p :listNewTwo)
        {
            p.burstTime=list2.get(p.name);
        }
        return listNewTwo ;
    }
    public static ArrayList<Process> nonPrimitiveSJF(ArrayList<Process> givenList)
    {
        int timeLine=0;
        ArrayList<Process> list=new ArrayList<>(givenList);
        ArrayList<Process> listNew=new ArrayList<>();
        ArrayList<Process> listNewTwo=new ArrayList<>();

        Sort.sortArrayList(list,0);

        while(timeLine!=Process.totalBurstTime) {
            for (Process process : list) {
                if (process.arrivalTime <= timeLine) {
                    listNew.add(process);
                } else
                    break;
            }

            if(listNew.size()==0)
                break;
            Sort.sortArrayList(listNew,1);
            int i=0;list.remove(listNew.get(i));


            listNew.get(i).startTime=timeLine;
            timeLine+=listNew.get(i).burstTime;
            listNew.get(i).waitingTime=listNew.get(i).startTime-listNew.get(i).arrivalTime;
            listNew.get(i).responseTime=listNew.get(i).waitingTime;
            listNew.get(i).turnAroundTime=listNew.get(i).waitingTime+listNew.get(i).burstTime;
            Process.totalTurnAroundTime+=listNew.get(i).turnAroundTime;
            Process.totalWaitingTime+=listNew.get(i).waitingTime;
            listNewTwo.add(listNew.get(i));
            listNew.clear();
        }
        return listNewTwo ;
    }
    public static ArrayList<Process> primitivePriority(ArrayList<Process> givenList)
    {
        Map<String ,Integer> list2=new HashMap<>();

        for(Process p:givenList)
        {
            list2.put(p.name,p.burstTime);
        }
        int timeLine=0;
        ArrayList<Process> list=new ArrayList<>(givenList);
        ArrayList<Process> listNew=new ArrayList<>();
        ArrayList<Process> listNewTwo=new ArrayList<>();

        Sort.sortArrayList(list,0);

        while(timeLine!=Process.totalBurstTime) {
            for (Process process : list) {
                if (process.arrivalTime <= timeLine) {
                    listNew.add(process);
                } else
                    break;
            }


            Sort.sortArrayList(listNew,2);

            final int  i=0;

            if(!listNew.get(i).startTimeCheck)
            {
                listNew.get(i).startTime=timeLine;
                listNew.get(i).startTimeCheck=true;
            }

            timeLine++;
            listNew.get(i).burstTime-=1;

            for(int j=1 ; j<listNew.size() ; j++)
            {
                listNew.get(j).waitingTime++;
                Process.totalWaitingTime++;
            }
            if(listNew.get(i).burstTime==0) {
                listNew.get(i).endTime=timeLine;
                listNew.get(i).responseTime=listNew.get(i).startTime-listNew.get(i).arrivalTime;
                listNew.get(i).turnAroundTime=listNew.get(i).endTime-listNew.get(i).arrivalTime;
                Process.totalTurnAroundTime+=listNew.get(i).turnAroundTime;
                list.remove(listNew.get(i));
            }
            else{
                int indexOfNewList=list.indexOf(listNew.get(i));
                list.set(indexOfNewList,listNew.get(i));
            }
            Process newProcess=listNew.get(i);
            listNewTwo.add(newProcess);

            listNew.clear();

        }

        for(Process p :listNewTwo)
        {
            p.burstTime=list2.get(p.name);
        }
        return listNewTwo ;
    }
    public static ArrayList<Process> nonPrimitivePriority(ArrayList<Process> givenList)
    {

        int timeLine=0;
        ArrayList<Process> list=new ArrayList<>(givenList);
        ArrayList<Process> listNew=new ArrayList<>();
        ArrayList<Process> listNewTwo=new ArrayList<>();

        Sort.sortArrayList(list,0);

        while(timeLine!=Process.totalBurstTime) {
            for (Process process : list) {
                if (process.arrivalTime <= timeLine) {
                    listNew.add(process);
                } else
                    break;
            }
            if(listNew.size()==0)
                continue;
            Sort.sortArrayList(listNew,3);
            int i=0;list.remove(listNew.get(i));

            listNew.get(i).startTime=timeLine;
            timeLine+=listNew.get(i).burstTime;
            listNew.get(i).waitingTime=listNew.get(i).startTime-listNew.get(i).arrivalTime;
            listNew.get(i).responseTime=listNew.get(i).waitingTime;
            listNew.get(i).turnAroundTime=listNew.get(i).waitingTime+listNew.get(i).burstTime;
            Process.totalTurnAroundTime+=listNew.get(i).turnAroundTime;
            Process.totalWaitingTime+=listNew.get(i).waitingTime;
            listNewTwo.add(listNew.get(i));
            listNew.clear();
        }

        return listNewTwo ;
    }
    public static ArrayList<Process> roundRobin(ArrayList<Process> givenList, int timeQuantum) {


        int timeLine=0;
        Map<String ,Integer> list2=new HashMap<>();

        for(Process p:givenList)
        {
            list2.put(p.name,p.burstTime);
        }
        ArrayList<Process> list=new ArrayList<>(givenList);
        ArrayList<Process> listNew=new ArrayList<>();
        Queue<Process> queueList=new LinkedList<>();

        Sort.sortArrayList(list,0);


        while(timeLine!=Process.totalBurstTime) {

            for (Process process : list) {
                if (process.arrivalTime <= timeLine) {
                    queueList.add(process);
                } else
                    break;
            }

            while(!queueList.isEmpty())
            {
                Process process=queueList.peek();

                if(!process.startTimeCheck) {
                    process.startTime=timeLine;
                    process.startTimeCheck=true;
                    process.responseTime=process.startTime;
                }

                if(process.burstTime<=timeQuantum) {
                    timeLine+=process.burstTime;
                    process.burstTime=0;

                }
                else {
                    timeLine+=timeQuantum;
                    process.burstTime-=timeQuantum;
                }

                if(process.burstTime==0) {
                    process.endTime=timeLine;
                    process.turnAroundTime=process.endTime-process.startTime;
                    Process.totalTurnAroundTime+=process.turnAroundTime;
                    list.remove(process);
                    int indexOfGivenList =list2.get(process.name);
                    process.waitingTime=process.turnAroundTime- indexOfGivenList;
                }
                else {
                    int indexOfList=list.indexOf(process);
                    list.set(indexOfList,process);
                }
                listNew.add(process);
                queueList.remove();
            }
        }
        for(Process p :listNew)
        {
            p.burstTime=list2.get(p.name);
        }
        return listNew ;
    }

}
