    import java.util.ArrayList;
    import java.util.Scanner;

    public class BankerProcess {
        String processName;
        ArrayList<Integer> allocation;
        ArrayList<Integer> maxNeed;
        static ArrayList<Integer> available;
        ArrayList<Integer> remainingNeed;
        static int totalProcess = 0;
        static int numberOfResources;
        private char nameOfResources='A';

        public BankerProcess(int numberOfResources) {
            numberOfResources = numberOfResources;
            this.processName = "P" + (++totalProcess);

            allocation = new ArrayList<>();
            maxNeed = new ArrayList<>();
            available = new ArrayList<>();
            remainingNeed = new ArrayList<>();

            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < numberOfResources; i++) {
                System.out.println("Enter the allocation of resource "+nameOfResources+" for " + processName + ":");
                allocation.add(scanner.nextInt());

                System.out.println("Enter the max need of resource "+ nameOfResources++ +" for " + processName + ":");
                maxNeed.add(scanner.nextInt());

                // Repeat the above steps for resource B, C, etc.
            }
        }
    }
