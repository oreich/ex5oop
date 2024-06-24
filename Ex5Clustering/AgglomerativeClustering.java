import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{
	double threshold;
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}
	public Set<Set<T>> clusterSet(Set<T> elements) {
		// TODO: Complete
		List<Set<T>> elementsList = elements.stream()
				.map(e -> {Set<T> elm = new HashSet<>();
									elm.add(e);
									return elm;})
				.collect(Collectors.toList());

		while (elementsList.size() > 1) {
			Optional<Map.Entry<Set<T>, Set<T>>> closest = IntStream.range(0, elementsList.size())
					.boxed()
					.flatMap(i -> IntStream.range(i+1, elementsList.size())
							.mapToObj(j -> Map.entry(elementsList.get(i), elementsList.get(j))))
					.min(Comparator.comparing(pair -> this.setDistance(pair.getKey(), pair.getValue())));
			if (setDistance(closest.get().getKey(), closest.get().getValue()) > threshold) {
				return elementsList.stream().collect(Collectors.toSet());
			}
			elementsList.remove(closest.get().getKey());
			elementsList.remove(closest.get().getValue());
			closest.get().getKey().addAll(closest.get().getValue());
			elementsList.add(closest.get().getKey());
		}
		return elementsList.stream().collect(Collectors.toSet());
	}

	private double setDistance(Set<T> setA, Set<T> setB) {
		return setA.stream()
				.flatMap(a -> setB.stream().map(b -> b.distance(a)))
				.min(Double::compare).orElse(Double.MAX_VALUE);
	}
}
