package library.data;

public class MusicItem extends Item  implements Comparable<MusicItem>{
	private Music music;

	public MusicItem(long id, Music music) {
		super(id);
		this.music = music;
	}

	public Music getMusic() {
		return music;
	}

	public Music getObject() {
		return getMusic();
	}
	
	@Override
	public String toString() {
		return "Music: " + music + super.toString();
	}
	
	@Override
	public int compareTo(MusicItem o) {
		return music.getTitle().compareTo(o.getMusic().getTitle());
	}
}
