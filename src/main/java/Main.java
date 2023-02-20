import enums.DetectingCode;
import model.CodeParameter;
import model.CyclicRedundancyCodeParameter;
import model.HammingCodeParameter;
import model.InternetChecksumParameter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import test.Test2;

import java.text.Normalizer;

public class Main {

    public static void main(String[] args) {
//        Test2.test(new CodeParameter(), DetectingCode.PARITY_BIT_CODE);
//        Test2.test(new CyclicRedundancyCodeParameter(), DetectingCode.CYCLIC_REDUNDANCY_CODE);
//        Test2.test(new InternetChecksumParameter(), DetectingCode.INTERNET_CHECKSUM);
//        Test2.test(new HammingCodeParameter(), DetectingCode.HAMMING_CODE);

        Options options = new Options();
        Option code = Option.builder("C")
                .longOpt("code")
                .desc("The code to use to encode and decode messages. Valid codes are: " + DetectingCode.getArgumentNamesForConsole())
                .required(true)
                .hasArg()
                .build();
        options.addOption(code);

        Option isBurstError = Option.builder("B")
                .longOpt("burstError")
                .desc("Specify if type of error is burst (default false)")
                .build();
        options.addOption(isBurstError);

        Option burstLength = Option.builder("BL")
                .longOpt("burstLength")
                .desc("Specify the length of the burst (only if burstError option was specified, default 3)")
                .hasArg()
                .build();
        options.addOption(burstLength);

        Option messageBitSize = Option.builder("MBS")
                .longOpt("messageBitSize")
                .desc("Specify the length of a message to be coded (default 8 for parity bit code and CRC, 16 for internet checksum and 4 for hamming)")
                .hasArg()
                .build();
        options.addOption(messageBitSize);

        Option minP = Option.builder("MinP")
                .desc("The minimum probability of a bit to be corrupted (default 0.01)")
                .hasArg()
                .build();
        options.addOption(minP);

        Option maxP = Option.builder("MaxP")
                .desc("The maximum probability of a bit to be corrupted (default 0.5)")
                .hasArg()
                .build();
        options.addOption(maxP);

        Option iterationsPerP = Option.builder("I")
                .desc("The number of iterations per probability (default 10000)")
                .hasArg()
                .build();
        options.addOption(iterationsPerP);

        Option nbStepPerP = Option.builder("S")
                .desc("The number of step between the minimum and the maximum probability (default 50)")
                .hasArg()
                .build();
        options.addOption(nbStepPerP);

        Option generatorPolynomial = Option.builder("GP")
                .longOpt("generatorPolynomial")
                .desc("The generator polynomial to use to encode messages (only if CRC code is chosen, default 1011)")
                .hasArg()
                .build();
        options.addOption(generatorPolynomial);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            DetectingCode detectingCode = parseCode((String) line.getParsedOptionValue(code));
            CodeParameter codeParameter = getCodeParameter(detectingCode, code, line);

            if (line.hasOption(burstLength)) {
                codeParameter.setBurstError(true);
            }

            if (line.hasOption(iterationsPerP)) {
                codeParameter.setNumberOfIterationsPerProbability(Integer.parseInt((String) line.getParsedOptionValue(iterationsPerP)));
            }

            Test2.test(codeParameter, detectingCode);
        } catch (ParseException | IllegalArgumentException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("myApp", options, true);
        }
    }

    private static CodeParameter getCodeParameter(DetectingCode detectingCode, Option code, CommandLine line) throws ParseException {
        CodeParameter codeParameter;
        switch (detectingCode) {
            case CYCLIC_REDUNDANCY_CODE -> codeParameter = new CyclicRedundancyCodeParameter();
            case INTERNET_CHECKSUM -> codeParameter = new InternetChecksumParameter();
            case HAMMING_CODE -> codeParameter = new HammingCodeParameter();
            default -> codeParameter = new CodeParameter();
        }
        return codeParameter;
    }

    public static DetectingCode parseCode(String code) {
        //https://www.baeldung.com/java-remove-accents-from-text
        code = Normalizer.normalize(code.trim().toLowerCase(), Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
        for (DetectingCode detectingCode : DetectingCode.values()) {
            if (code.equals(detectingCode.getArgumentName().toLowerCase())) {
                return detectingCode;
            }
        }
        throw new IllegalArgumentException("The code given in parameter is not valid: " + code);
    }
}
