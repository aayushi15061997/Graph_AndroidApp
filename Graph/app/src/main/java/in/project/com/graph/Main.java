package in.project.com.graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Main {
	
	int  n;
    int  m;
	ArrayList<Integer>[] g=new ArrayList[100];
	
	int val[]=new int[100];
	int low[]=new int [100];
    int T = 1;
	Stack<Integer> p=new Stack<>();
	int  ctr = 0;
	int  bcc[]=new int[100];
	ArrayList<Integer>[] GG=new ArrayList[100];
	
    int min(int i, int j) {
		if(i<j)
			return i;
		else
			return j;
	
	}
	void dfs(int  x , int  pa){
		
	    val[x] = low[x] = T ++;
	    p.push(x);
	    for(int i=0;i<g[x].size();i++){
	    	
	    	int y=g[x].get(i);
	    	
	        if(x == y) continue;
	        if(val[y] == 0){
	            dfs(y , x);
	            low[x] = min(low[x] , low[y]);
	        }
	        else low[x] = min(low[x] , val[y]);
	    }
	    if(low[x] == val[x]){
	        ctr ++;
	        while(p.peek() != x){
	            bcc[p.peek()] = ctr;
	            p.pop();
	        }
	        bcc[p.peek()] = ctr;
	        p.pop();
	    }
	}
	int  D[]=new int[100];

    Set<pair> st=new HashSet<>();
	void dfs2(int  x , int  pa){
	    for(int  i = 0; i <= ctr; i ++) D[i] = 999;
	    D[x] = 0;
	    Queue< Integer> Q=new LinkedList<>();
	    Q.add(x);
	    while(!Q.isEmpty()){
	        int  xx = Q.peek();
	        Q.remove();
	        for(int j=0;j<GG[xx].size();j++){
	        	
	        	int y=GG[xx].get(j);
	            if(D[y] > D[xx] + 1){
	                D[y] = D[xx] + 1;
	                Q.add(y);
	            }
	        }
	    }
	}
	ArrayList<Integer> from;
    ArrayList<Integer> to;
    public Main(int n,ArrayList<Integer> from,ArrayList<Integer> to){
        this.n=n;
        this.from=from;
        this.to=to;
        this.m=from.size();
    }
    public boolean execute(int start,int end) {

    	
    	for(int i=0;i<100;i++)
    	{	
    		g[i]=new ArrayList<>();
    		GG[i]=new ArrayList<>();
    	}

	    for(int i = 0; i <m; i ++){
	        int x,y;
	    
	    x=from.get(i);
	    y=to.get(i);
	    
	        g[x].add(y);
	        pair p=new pair(x,y);
	        st.add(p);
	    }
	    for(int i = 1; i <= n; i ++){
	        if(val[i] == 0) dfs(i , -1);
	    }
	    
	    	
	    for(int  i = 1; i <= n; i ++){
	        for(int j=0; j< g[i].size();j++){
	        	
	        	int y=g[i].get(j);
	            if(bcc[i] == bcc[y]){
	                continue;
	            }
	            else{
	            		
	            	Iterator<pair> it=st.iterator();
	            	int flag=0;
	            	while(it.hasNext())
	            	{
	            		pair temp=it.next();
	            		
	            		if(temp.x==i&&temp.y==y)
	            		{
	            			GG[bcc[i]].add(bcc[y]);
	            			flag=1;
	            			break;
	            		}
	            	}
	            	
	                if(flag==0) 
	                GG[bcc[y]].add(bcc[i]);
	            }
	        }
	    }
	        int  x , y; 
	        x=start;
	        y=end;

        boolean ans;
	   
	        if(bcc[x] == bcc[y])
            {
                System.out.println("Yes");
                ans=true;
            }
	        else{
	            dfs2(bcc[x] , -1);
	           
	            if(D[bcc[y]] != 999)
                {
                    System.out.println("Yes");
                    ans=true;
                }
                else
                {
                    System.out.println("No");
                    ans=false;
                }
	        }

    	return ans;
    }
}
