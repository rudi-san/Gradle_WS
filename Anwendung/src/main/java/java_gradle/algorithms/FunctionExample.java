package java_gradle.algorithms;

import java.util.function.Function;


public class FunctionExample {
	
	private static Function<int[], Integer> add 		= (int[] i) -> {return (i[0] + i[1]);};
	private static Function<int[], Integer> multiply 	= (int[] i) -> {return (i[0] * i[1]);};
	private static Function<int[], Integer> potenz   	= (int[] i) -> 
						{ 
							if 	(i[1]==0)	return 1;
							int x			= i[0];
							int y			= x;
							for (int j=1;j<i[1];++j) 
								x *= y; 
							return 			x;
						};
	
	public static Function<int[], Integer> getFunction (String funcName) throws Exception {

		switch (funcName) {
		case "add": 		return add;
		case "multiply": 	return multiply;
		case "potenz":      return potenz;
		default:			throw new Exception("Function '"+funcName+"' ist nicht implementiert");
		}
	}
	
	
	public static int rechne (int[] values, Function<int[], Integer> func) {
		return 			func.apply(values);
	}
}
