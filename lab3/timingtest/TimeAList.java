package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Target=new AList<>() ;
        AList<Integer> Ns=new AList<>() ;
        AList<Double> times=new AList<>() ;
        int preind=0;
        int index=0;
        Stopwatch sw=new Stopwatch();



        for(int exp=0;exp<8;exp++){
            for(index=preind;index<1000*Math.pow(2,exp);index++){
                Target.addLast(1);
            }
            times.addLast(sw.elapsedTime());
            Ns.addLast(index);

        }
        TimeAList.printTimingTable(Ns,times,Ns);
    }
}
