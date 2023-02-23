import enums.DetectingCode;
import model.*;
import org.apache.commons.cli.*;
import test.Test2;

import java.text.Normalizer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Test2.test(new CodeParameter(), DetectingCode.PARITY_BIT_CODE);
//        Test2.test(new CyclicRedundancyCodeParameter(), DetectingCode.CYCLIC_REDUNDANCY_CODE);
//        Test2.test(new InternetChecksumParameter(), DetectingCode.INTERNET_CHECKSUM);
//        Test2.test(new HammingCodeParameter(), DetectingCode.HAMMING_CODE);

        askCommand();
        if (true)
            return;

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
            CodeParameter codeParameter = getCodeParameter(detectingCode);

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

    private static CodeParameter getCodeParameter(DetectingCode detectingCode) throws ParseException {
        CodeParameter codeParameter;
        switch (detectingCode) {
            case CYCLIC_REDUNDANCY_CODE -> codeParameter = new CyclicRedundancyCodeParameter();
            case INTERNET_CHECKSUM -> codeParameter = new InternetChecksumParameter();
            case HAMMING_CODE -> codeParameter = new HammingCodeParameter();
            default -> codeParameter = new CodeParameter();
        }
        return codeParameter;
    }

    private static void askCommand() {
        try (Scanner scanner = new Scanner(System.in)) {
            String mainCommand;
            String subCommand;
            String[] splitCommand;
            CommandLineParser parser = new DefaultParser();
            ProgramOption programOption = new ProgramOption();
            Options options;
            HelpFormatter formatter = new HelpFormatter();
            printUsage();
            do {
                System.out.println("Enter a command: ");
                mainCommand = scanner.nextLine();
                if ("exit".equals(mainCommand)) {
                    return;
                }
                switch (mainCommand) {
                    case "encode":
                    case "decode":
                        options = programOption.getEncodeDecodeOptions();
                        break;
                    case "generateMessage":
                        options = programOption.getGenerateMessageOptions();
                        break;
                    case "corruptMessage":
                        options = programOption.getCorruptMessageOptions();
                        break;
                    case "generateGraph":
                        options = programOption.getGraphOptions();
                        break;
                    default:
                        System.out.println("Couldn't recognize the command");
                        printUsage();
                        continue;
                }
                formatter.printHelp(mainCommand, options, true);
                subCommand = scanner.nextLine();
                splitCommand = subCommand.split(" ");
            } while (true);
        }
    }

    private static void printUsage() {
        System.out.println("Usage : ");
        System.out.println("exit: exit the program");
        System.out.println("encode: encode a message ");
        System.out.println("generateGraph: generate a graph showing the detecting/correcting error rate of a specified code");
        System.out.println();
    }

    private static void generateGraph(CommandLineParser parser, Options options, String[] splitCommand) {
        try {
            CommandLine line = parser.parse(options, splitCommand);
            DetectingCode detectingCode = parseCode((String) line.getParsedOptionValue(ProgramOption.CODE));
            CodeParameter codeParameter = getCodeParameter(detectingCode);

//            if (line.hasOption(burstLength)) {
//                codeParameter.setBurstError(true);
//            }

//            if (line.hasOption(iterationsPerP)) {
//                codeParameter.setNumberOfIterationsPerProbability(Integer.parseInt((String) line.getParsedOptionValue(iterationsPerP)));
//            }

            Test2.test(codeParameter, detectingCode);
        } catch (ParseException | IllegalArgumentException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
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
