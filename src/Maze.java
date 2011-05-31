import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Random;

//Michael Field
//5/31/2011
//This is my maze class, which has methods for traversing a maze, making a maze, and tracing a maze

public class Maze 
{
		//each maze can be designated by two coordinates x(across) and y(down).
		private int width;
		private int height;
		private char[]charArray;
		
		private char[][]mazeArray;
		private Stack<Integer> solvePathX=new Stack<Integer>();
		private Stack<Integer> solvePathY=new Stack<Integer>();
		private LinkedList<Integer>tracePathX=new LinkedList<Integer>();
		private LinkedList<Integer>tracePathY=new LinkedList<Integer>();
		private LinkedList<Point>border=new LinkedList<Point>();
		private Stack<Integer> makePathX=new Stack<Integer>();
		private Stack<Integer> makePathY=new Stack<Integer>();
		//private LinkedList<Integer>borderY=new LinkedList<Integer>();
		private Random rand = new Random();
		//private int randomBorderX = rand.nextInt(width);
		//private int randomBorderY = rand.nextInt(height);
		private int begX;
		private int begY;
		private Point begPoint;
		private int endX;
		private int endY;
		private int counter;
		private Point endPoint;
		private boolean over=false;
		private boolean made=false;
	//my constructor 
	public Maze(int x, int y)throws IndexOutOfBoundsException
	{
		if(x>1&&y>1)
		{
		this.width=3+2*(x-1);
		
		this.height=3+2*(y-1);
		}
		this.mazeArray= new char[width][height];
		counter=0;
	}
	//loads a maze in a 2d array from a String
	public void load(String userString)
	{
		int counter=0;
		
		charArray=userString.toCharArray();
		
		System.out.println(charArray);
		
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				//puts the value of the character in the place of the maze array
				mazeArray[x][y]=charArray[counter];
				counter++;			
			}
		}
	}
	
	//Displays the array using print
	public void display()
	{
		
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				System.out.print(mazeArray[x][y]);		
			}
			System.out.println();
		}
	}
	// My solve method, uses backwards recursive algorithm
	public boolean solve(int begX,int begY,int endX,int endY)
	{
		over=false;
		//makes sure you are not starting in a wall
		//System.out.println("Your coordinants are: "+ begX+" "+begY+".");
		//System.out.println("The value at this coordinant is "+mazeArray[begX][begY]+".");
		//System.out.println(tracePathX.toString());
		//System.out.println(tracePathY.toString());
		
		//System.out.println(begX);
		//System.out.println(begY);
		//System.out.println(endX);
		//System.out.println(endY);
		//System.out.println(mazeArray[begX][begY]);
		
		
		//if you are not glitched in a wall.
		//base case
		if(begX==endX&&begY==endY)
		{
			System.out.println(begX);
			System.out.println(begY);
			System.out.println(endX);
			System.out.println(endY);
			System.out.println("Yo!!!!");
			//vfixer();
			over=true; 
			return over;
		}
		else if((mazeArray[begX][begY])=='0'||(mazeArray[begX][begY])=='v')
		{
			System.out.println("I get this far!");
			//we've visited this spot before!
			mazeArray[begX][begY]=('v');
			//two stacks
			// i use a recursive backtracer algorithm
			//moves the coordinante to the top of the stack
			solvePathX.push(begX);
			solvePathY.push(begY);
			tracePathX.add(begX);
			tracePathY.add(begY);
			
			
				
				//down
			if(begY!=height-1)
			{
				//System.out.println("You will always meet this condition for the first maze");
				if(mazeArray[begX][begY+1]=='0')
				{
					
					//System.out.println("I'm going down!");
					solve(begX,begY+1,endX,endY);
				}
				//since this is else if you will go down here and potentially not try something else
				
			}
			//so if you go out of bounds
			//do it again but go the other way
			
			
		
			//left
		
		if(begX!=0)
		{
			if(mazeArray[begX-1][begY]=='0')
			{
				//System.out.println("I'm going left!");
				solve(begX-1,begY,endX,endY);
			}
		}
		
		//right
		
		 if(begX!=width-1)
		{
			if(mazeArray[begX+1][begY]=='0')
			{
				//System.out.println("I'm going right!");
				solve(begX+1,begY,endX,endY);		
			}
		}
		
		//up	
		 if(begY!=0)
		{
			if(mazeArray[begX][begY-1]=='0')
			{
				//System.out.println("I'm going up!");
				solve(begX,begY-1,endX,endY);
			}
	
		}
		else
		{
			
				//System.out.println("It needs to go backwards now!");
				solvePathX.pop();//removes the first coordinate from the stack, i need to do this twice for it to work
				solvePathY.pop();
				
				//if the stacks arent empty;
				//if the stacks were empty it signifies that there is no escape and the position is surrounded by 4 walls.
				if(!solvePathX.isEmpty()&&!solvePathY.isEmpty())
				{
					solve(solvePathX.pop(),solvePathY.pop(),endX,endY);
				}
				else
				{
					over=false;
					System.out.println("I'm trapped! Oh no!");
					return over;
				}
			}
			
		}
		//System.out.println("It goes all the way down here!");
		return over;
		}

	//just prints out the path of the solve
	public void trace(int begX,int begY,int endX,int endY)
	{
		int counter=1;
		solve(begX,begY,endX,endY);
		System.out.print("The path to get to the desired point: ");
		for(int i=0; i<tracePathX.size(); i++)
		{	
			System.out.print(counter+". ("+tracePathX.get(i)+","+tracePathY.get(i)+") ");
			counter++;
		}
		System.out.println();
	}
	//goes through the matrix and resets the entire maze to walls. 
	public void reset()
	{
		tracePathX.clear();
		tracePathY.clear();
		border.clear();
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				//puts the value of the character in the place of the maze array
				//this finds if the specified wall is on teh border
				if(x==0&&y==0||x==width-1&&y==0||x==0&&y==height-1||x==width-1&&x==height-1)
				{
					mazeArray[x][y]='1';	
				}
				else if(x<width-1&&y==0||x<width-1&&y==height-1||x==0&&y<height||x==width-1&&y<height)
				{
					
					mazeArray[x][y]='1';
					//I should all he locations ofthe border to a list 
					//adds coordinents to lists of the border coordinents 
					Point spot=new Point(x,y);
					border.add(spot);
					//System.out.println(border.toString());
				}
				else	
				{
					mazeArray[x][y]='0';
				}
			}
		}
	}
	//picks a random starting point
	public void pickStartingPoint()
	{
		//randomly shuffles the list
		Collections.shuffle(border);
		//gets and removes the first element from teh list
		begPoint=border.getFirst();
		border.removeFirst();
		//changes the maze to have an open entrance
		mazeArray[(int)begPoint.getX()][(int)begPoint.getY()]='0';
		System.out.println(begPoint.toString());
		begX=(int) begPoint.getX();
		begY=(int) begPoint.getY();
	}
	//picks a random ending point
	public void pickEndingPoint()
	{
		//randomly shuffles the list
		Collections.shuffle(border);
		//gets and removes the first element from teh list
		endPoint=border.getFirst();
		border.removeFirst();
		//changes the maze to have an open exit
		mazeArray[(int)endPoint.getX()][(int)endPoint.getY()]='0';
		System.out.println(endPoint.toString());
		endX=(int) endPoint.getX();
		endY=(int) endPoint.getY();
	}
	//differentiates the border from the rest of the mazed

	
	//comes up with a new pattern for the maze
	public void changeInnerMaze()
	{
		Random chance = new Random();
		
		reset();
		pickStartingPoint();
		pickEndingPoint();
		for(int y=1; y<height-1; y++)
		{
			for(int x=1; x<width-1; x++)
			{
				int choice=chance.nextInt(2);
				if(choice==0)
				{
					mazeArray[x][y]='0';
				}
				else if(choice==1)
				{
					mazeArray[x][y]='1';
				}
			}	
		}
	}
	//comes up with a new working maze
	public void redesign()
	{
			changeInnerMaze();
			display();
			
			//System.out.println(begPoint);
			//System.out.println(endPoint);
			//System.out.println(begX);
			//System.out.println(begY);
			//System.out.println(endX);
			//System.out.println(endY);
		//	System.out.println("monkey!");
		//go through the not border area of the maze.
			boolean working=solve(begX,begY,endX,endY);
			System.out.println(working);
		if(working==false)
		{
			//System.out.println("Does it ever get here?");
			//do it again untill it work
			redesign();
		}
	}
	//fixes the vs the solve method creates
	public void vfixer()
	{
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				if(mazeArray[x][y]=='v')
				{
					mazeArray[x][y]='0';
				}
			}
		}
	}
	//accessor methods
	public int getBegX()
	{
		return begX;
	}
	public int getBegY()
	{
		return begY;
	}
	public int getEndX()
	{
		return endX;
	}
	public int getEndY()
	{
		return endY;
	}
}
