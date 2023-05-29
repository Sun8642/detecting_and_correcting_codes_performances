# Correcting and detecting codes: benchmark

This application implements different codes allowing the detection and correction of errors in a binary message.

## Implemented codes:

- Parity bit
- Cyclic redundancy check
- Internet checksum
- Hamming

These codes are implemented for multiple data structures:

- BigInteger
- StringBuilder
- BigInt

The classes contained in the benchmark.binary.operation package can be used to display a graph showing the evolution of
the execution time of the binary operations of the various structures as a function of the size (in bits) of a message.
The classes contained in the benchmark.code also allow to display the same kind of graph.

The class StructureMemoryConsumptionRunner display in the console the memory used by the data structures:

- BigInteger
- StringBuilder
- BigInt