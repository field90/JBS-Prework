import java.util.Scanner;

//111111111100010001111010101100010101101110101100000101111011101100000101111111111
public class MazeReader 
{

	public static void main(String []args)
	{
		Maze lab= new Maze(4,4);
		Scanner console = new Scanner(System.in); 
		System.out.println("Give me a String of 1s and 0s that you can enter.");
		String filename = console.nextLine();
		//next line is the maze
		lab.load(filename);
		//lab.reset();
		lab.display();
		System.out.println(lab.solve(1,1,7,7));
		lab.display();
		lab.trace(1, 1, 7, 7);
		lab.display();
		System.out.println();
		lab.reset();
		lab.display();
		lab.pickStartingPoint();
		lab.pickEndingPoint();
		lab.display();
		//lab.redesign();
		
		System.out.println(lab.solve(lab.getBegX(), lab.getBegY(), lab.getEndX(), lab.getEndY()));
		lab.display();
		lab.trace(lab.getBegX(), lab.getBegY(), lab.getEndX(), lab.getEndY());
		lab.display();
		lab.changeInnerMaze();
		//display();
		//lab.redesign();
		System.out.println("This is the new maze:");
		lab.display();
		lab.redesign();
		System.out.println("This is the redesigned maze:");
		lab.display();
		
		lab.vfixer();
		System.out.println("This should be the redesigned maze without the vs");
		lab.display();
		System.out.println("No errors woot!!");
				

	}
	
}
