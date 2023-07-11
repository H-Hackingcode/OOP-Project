import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void mainBanker() {
        Queue<BankerProcess> processQueue = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numberOfProcesses = scanner.nextInt();

        System.out.print("Enter the number of Resources: ");
        int numberOfResources = scanner.nextInt();

        for (int i = 0; i < numberOfProcesses; i++) {
            BankerProcess process = new BankerProcess(numberOfResources);
            processQueue.add(process);
        }

        System.out.println("Enter the available resources:");

        for (int i = 0; i < numberOfResources; i++) {
            System.out.print("Available Resource " + (char) ('A' + i) + ": ");
            BankerProcess.available.add(scanner.nextInt());
        }

        ArrayList<String> safeSequence = bankerAlgorithm(processQueue);

        System.out.println("Safe Sequence:");
        for (String processName : safeSequence) {
            System.out.print(processName + " ");
        }
    }

    public static ArrayList<String> bankerAlgorithm(Queue<BankerProcess> processQueue) {
        ArrayList<String> safeSequence = new ArrayList<>();

        int numberOfResources = BankerProcess.numberOfResources;
        String availableResources="";
        for(int i =0 ; i<numberOfResources ; i++)
        {
            availableResources+=BankerProcess.available.get(i);
        }
        int availableResourcesInt=Integer.parseInt(availableResources);


        while(!processQueue.isEmpty())
        {

            String tempStringOne="";
            String tempStringTwo="";
            for (int i =0 ; i<numberOfResources ; i++){
                processQueue.peek().remainingNeed.add(processQueue.peek().maxNeed.get(i)-processQueue.peek().allocation.get(i));
                tempStringOne+= processQueue.peek().remainingNeed.get(i);
                tempStringTwo+=processQueue.peek().allocation.get(i);
            }
            if(Integer.parseInt(tempStringOne)<=availableResourcesInt)
            {
                safeSequence.add(processQueue.peek().processName);
                availableResourcesInt+=Integer.parseInt(tempStringTwo);
                processQueue.remove();
            }
            else
            {
                processQueue.add(processQueue.peek());
                processQueue.remove();
            }

        }

        return safeSequence;
    }
}
