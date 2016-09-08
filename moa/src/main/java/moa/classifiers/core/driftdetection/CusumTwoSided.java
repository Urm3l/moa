package moa.classifiers.core.driftdetection;

import com.github.javacliparser.FloatOption;
import com.github.javacliparser.IntOption;
import moa.core.ObjectRepository;
import moa.tasks.TaskMonitor;

public class CusumTwoSided extends AbstractChangeDetector {

    private static final long serialVersionUID = -3518369648142099719L;

    public IntOption minNumInstancesOption = new IntOption(
            "minNumInstances",
            'n',
            "The minimum number of instances before permitting detecting change.",
            30, 0, Integer.MAX_VALUE);

    public FloatOption deltaOption = new FloatOption("delta", 'd',
            "Delta parameter of the Cusum Test", 0.005, 0.0, Float.MAX_VALUE);

    public FloatOption lambdaOption = new FloatOption("lambda", 'l',
            "Threshold parameter of the Cusum Test", 50, 0.0, Float.MAX_VALUE);

    private int m_n;

    public double sum;
    public double sumP;
    public double sumN;

    public double x_mean;

    private double alpha;

    private double delta;

    private double lambda;

    public CusumTwoSided() {
        resetLearning();
    }

    @Override
    public void resetLearning() {
        m_n = 1;
        x_mean = 0.0;
        sumP = 0.0;
        sumN = 0.0;
        sum = 0.0;
        delta = this.deltaOption.getValue();
        lambda = this.lambdaOption.getValue();
    }

    @Override
    public void input(double x) {
        // It monitors the error rate
        if (this.isChangeDetected || !this.isInitialized) {
            resetLearning();
            this.isInitialized = true;
        }

        x_mean = x_mean + (x - x_mean) / (double) m_n;

        sumP = Math.max(0, sumP + (x - x_mean) - this.delta);
        sumN = Math.max(0, sumN - (x - x_mean) - this.delta);
        sum = sum + (x - x_mean) - this.delta;




        m_n++;

        // System.out.print(prediction + " " + m_n + " " + (m_p+m_s) + " ");
        this.estimation = x_mean;
        this.isChangeDetected = false;
        this.isWarningZone = false;
        this.delay = 0;

        if (m_n <= this.minNumInstancesOption.getValue()) {
            return;
        }

        if (sumP > this.lambda || sumN > this.lambda) {
            this.isChangeDetected = true;
        }
    }

    @Override
    public void getDescription(StringBuilder sb, int indent) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void prepareForUseImpl(TaskMonitor monitor,
                                     ObjectRepository repository) {
        // TODO Auto-generated method stub
    }
}
