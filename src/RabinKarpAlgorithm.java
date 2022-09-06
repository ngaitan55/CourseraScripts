import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RabinKarpAlgorithm {
	
	public static List<Integer> runRabinKarp(String pattern, String text ) {
		List<Integer> answer = new ArrayList<>();
		long p = 1000000007;
		long x = 31;
		int m = pattern.length();
		int n = text.length();
		int k = n - m + 1;
		Long patternHash = hashCode(pattern,x,p);
		System.out.println(patternHash);
		Long[] hashes = new Long[k];
		precomputeHashes(hashes, pattern, text, p, x, m, k);
		for(int i = 0; i < k; i++) {
			Long substringHash = hashes[i];
			if(substringHash == patternHash) {
				String subString = text.substring(i, m+i);
				if(subString.equals(pattern)) answer.add(i);
			}
		}
		return answer;
	}
	
	private static Long[] precomputeHashes(Long[] hashes, String pattern, String text, long p, long x, int m, int k) {
		// TODO Auto-generated method stub
		hashes[k-1] = hashCode(pattern, x, p);
		System.out.println(hashes[k-1] );
		long y = (long) (Math.pow(x, m)%p);
		for(int i = k-2; i >= 0; i--) {
			calculateRecursiveHash(hashes, text, i, x, y, m, p);
		}
		return hashes;
	}
	
	private static void calculateRecursiveHash(Long[] hashes, String text, int i, long x, long y, int m, long p) {
		// TODO Auto-generated method stub
		Long hash = (x*hashes[i+1]) + (int) text.charAt(i) - (y* (int) text.charAt(i+m));
		System.out.println(i + " " + hash);
		hash = hash%p;
		hashes[i] = hash;
	}

	public static Long hashCode(String sequence, Long x, Long p){
		    //k.foreach(c => (println(c + " ascii is=" + c.toInt)))
		    Long hCode = (long) 0;
		    for(int i = 0; i<sequence.length();i++){
		      Long current = (long) (sequence.charAt(i)* Math.pow(x,i));
		      //println(k.charAt(i) + "=" + current)
		      hCode += current;
		    }
		    //println(k + " prev=" + hCode + " hash code is=" + (hCode%p)%m.toInt)
		    return (hCode%p);
		  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String pattern = sc.next();
		String text = sc.next();
		List<Integer> answer = runRabinKarp(pattern, text);
		for(int i = 0; i < answer.size();i++) {
			if(i == answer.size()-1) System.out.println(answer.get(i));
			else System.out.print(answer.get(i) + " ");
		}
	}

}
