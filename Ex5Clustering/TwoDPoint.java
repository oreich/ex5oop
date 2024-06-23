import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.lang.Math;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;
	public TwoDPoint(String str){
		// TODO: Complete
		String[] split = str.split(",");
		this.x = Double.parseDouble(split[0]);
		this.y = Double.parseDouble(split[1]);
	}
	public TwoDPoint(double x, double y) {
		// TODO: Complete
		this.x = x;
		this.y = y;
	}
	@Override
	public double distance(TwoDPoint other) {
		// TODO: Complete
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}

	public static Set<TwoDPoint> readClusterableSet(String path) throws IOException{
		// TODO: Complete
		Stream<String> stream = Files.lines(Paths.get(path));
		Set<TwoDPoint> points = stream
				.map(TwoDPoint::new)
				.collect(Collectors.toSet());
		return points;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public boolean equals(Object other) {
		TwoDPoint otherP = (TwoDPoint) other;
		return (otherP.x == x && otherP.y == y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
