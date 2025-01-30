package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        SLList<Integer> Target=new SLList<>();
        AList<Integer> Ns=new AList<>() ;
        AList<Double> times=new AList<>() ;
        AList<Integer> opCounts=new AList<>() ;
        int preind=0;
        int index=0;


        for(int exp=0;exp<8;exp++){
            for(index=preind;index<1000*Math.pow(2,exp);index++){
                Target.addLast(1);
            }
            Stopwatch sw=new Stopwatch();
            for(int i=0;i<10000;i++)
                Target.getLast();
            times.addLast(sw.elapsedTime());
            Ns.addLast(index);
            opCounts.addLast(10000);

        }
        TimeSLList.printTimingTable(Ns,times,opCounts);




    }

}
