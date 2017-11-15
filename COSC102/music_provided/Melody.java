public class Melody{
	private QueueInterface<Note> ourSong;
	private double duration;
	
	public Melody(QueueInterface<Note> song){
		ourSong=song;
		duration=calcDuration();
	}

	public double getTotalDuration(){
		return duration;
	}
	
	public String toString(){
		String songWords = "";
		QueueInterface<Note> tempSong = new LinkedQueue<Note>();
		while(!(ourSong.isEmpty())){
			Note temp = ourSong.dequeue();
			songWords+=temp.toString()+"\n";
			tempSong.enqueue(temp);
		}
		ourSong=tempSong;
		return songWords;
		
	}
	
	public void changeTempo(double tempo){
		QueueInterface<Note> tempSong = new LinkedQueue<Note>();
		while(!(ourSong.isEmpty())){
			Note temp = ourSong.dequeue();
			temp.setDuration(temp.getDuration()*tempo);
			tempSong.enqueue(temp);
		}
		//System.out.println(tempo*duration);
		ourSong=tempSong;
		duration=tempo*duration;
	}
	
	public void reverse(){
		StackInterface<Note> tempStack = new VectorStack<Note>();
		while(!(ourSong.isEmpty())){
			tempStack.push(ourSong.dequeue());
		}
		while(!(tempStack.isEmpty())){
			ourSong.enqueue(tempStack.pop());
		}
	}
	
	public void append(Melody other){
		while(!(other.ourSong.isEmpty())){
			ourSong.enqueue(other.ourSong.dequeue());
		}
		duration=this.duration+other.duration;
	}
	
	public void play(){
		boolean inRepeat=false;
		QueueInterface<Note> tempSong = new LinkedQueue<Note>();
		QueueInterface<Note> repeat = new LinkedQueue<Note>();
		while(!(ourSong.isEmpty())){
			Note temp = ourSong.dequeue();
			temp.play();
			if(temp.isRepeat()){
				if(!inRepeat){
					inRepeat=true;
					repeat.enqueue(temp);
				}
				else{
					inRepeat=false;
					repeat.enqueue(temp);
					while(!(repeat.isEmpty())){
						repeat.dequeue().play();
					}
				}
			}
			if(inRepeat){
				repeat.enqueue(temp);
			}
			tempSong.enqueue(temp);
		}
		ourSong=tempSong;
	}
	
	public double calcDuration(){
		double totalTime=0.0;
		boolean inRepeat=false;
		QueueInterface<Note> tempSong = new LinkedQueue<Note>();
		while(!(ourSong.isEmpty())){
			Note temp = ourSong.dequeue();
			if(temp.isRepeat()&&inRepeat){
				//System.out.println("hello");
				inRepeat= !(inRepeat);
				totalTime+=temp.getDuration();
			}else if(temp.isRepeat()&&!(inRepeat)){
				inRepeat= !(inRepeat);
			}
			if(inRepeat)
				totalTime+=temp.getDuration();
			totalTime+=temp.getDuration();
			tempSong.enqueue(temp);
			//System.out.println(totalTime);
		}
		ourSong=tempSong;
		return totalTime;	
	}
		
}