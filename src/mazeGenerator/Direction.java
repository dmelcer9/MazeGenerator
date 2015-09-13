package mazeGenerator;

public enum Direction{
		
		N,S,E,W;
		
		Direction opposite;
		
		static{
			N.opposite=S;
			S.opposite=N;
			W.opposite=E;
			E.opposite=W;
		}
}