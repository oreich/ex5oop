import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BitArray implements Clusterable<BitArray>{
	private ArrayList<Boolean> bits;

	public BitArray(String str){
		// TODO: Complete
		String[] splitedStr = str.split(" ");
		this.bits = Stream.of(splitedStr).map(b -> Objects.equals(b, "true")                )
				.collect(Collectors.toCollection(ArrayList::new));

	}
	public BitArray(boolean[] bits) {
		// TODO: Complete
		this.bits = IntStream.range(0, bits.length)
				.mapToObj(i -> bits[i])
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public double distance(BitArray other) {
		// TODO: Complete
		if (this.bits.size() != other.bits.size()) {return Double.POSITIVE_INFINITY;}
		double dist = (double) IntStream.range(0, this.bits.size())
				.filter(i -> !(this.bits.get(i).equals(other.bits.get(i)))).count();
		return dist;
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		// TODO: Complete. If the file contains bitarrays of different lengths,
		//  retain only those of maximal length
		int max_length = Files.lines(Paths.get(path))
				.map(l -> l.split(","))
				.map(l -> l.length)
				.max(Integer::compareTo).orElse(0);
		Set<BitArray> bitArrays = Files.lines(Paths.get(path))
				.map(l -> l.split(","))
				.filter(l -> l.length == max_length)
				.map(l -> String.join(" ",l))
				.map(BitArray::new)
				.collect(Collectors.toSet());
		return bitArrays;
	}

	@Override
	public String toString() {
		return bits.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BitArray bitArray = (BitArray) o;
		return bits.equals(bitArray.bits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bits);
	}
}
