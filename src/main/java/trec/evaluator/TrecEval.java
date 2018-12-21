package trec.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrecEval extends AbstractEvaluator {

    private static final String TREC_EVAL_SCRIPT = "/Users/jian-0526/Downloads/trec_eval.8.1/trec_eval";

    /**
     * -m all_trec -q -c -M1000
     */
    private static final String COMMAND = TREC_EVAL_SCRIPT + " -q -c -M1000";


    public TrecEval(File goldStandard, File results) {
        super.goldStandard = goldStandard;
        super.results = results;
        evaluate();
    }

    public List<String> getFullCommand() {
        List<String> fullCommand = new ArrayList<>();
        fullCommand.add(COMMAND);
        fullCommand.add(goldStandard.getAbsolutePath());
        fullCommand.add(results.getAbsolutePath());
        return fullCommand;
    }

    public static boolean scriptExists() {
        return new File(TREC_EVAL_SCRIPT).isFile();
    }
}
