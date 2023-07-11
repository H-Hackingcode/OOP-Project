import java.util.ArrayList;
public class Sort {
    public static ArrayList<Process> sortArrayList(ArrayList<Process> list ,int choice)
    {
        if(choice ==0)
        {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size(); j++) {
                    if (list.get(i).arrivalTime > list.get(j).arrivalTime) {
                        Process tempProcess = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, tempProcess);
                    }
                }
            }
        }
        else if (choice == 1)
        {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size(); j++) {
                    if (list.get(i).burstTime > list.get(j).burstTime) {
                        Process tempProcess = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, tempProcess);
                    }
                }
            }

        }
        else if(choice==2)
        {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size(); j++) {
                    if (list.get(i).priority > list.get(j).priority) {
                        Process tempProcess = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, tempProcess);
                    }
                }
            }

        }
        else if(choice==3)
        {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size(); j++) {
                    if (list.get(i).priority < list.get(j).priority) {
                        Process tempProcess = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, tempProcess);
                    }
                }
            }

        }
        return list;
    }
}
