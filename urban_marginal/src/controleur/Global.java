package controleur;

public interface Global{
	
	public static final int PORT = 6666 ;
	public static final String SEPARATOR = "//" ;
	public static final String CHEMIN = "media" + SEPARATOR ;
	public static final String CHEMINFONDS = CHEMIN + "fonds" + SEPARATOR ;
	public static final String FONDCHOIX = CHEMINFONDS + "fondchoix.jpg" ;
	public static final int GAUCHE = 0 ;
	public static final int DROITE = 1 ;
	public static final int HAUT = 2 ;
	public static final int BAS = 3 ;
	public static final int TIRE = 4 ;
	public static final int ACTION = 2 ;
	public static final String CHEMINPERSOS = CHEMIN + "personnages" + SEPARATOR ;
	public static final String PERSO = CHEMINPERSOS + "perso" ;
	public static final String EXTIMAGE = ".gif" ;
	public static final String MARCHE = "marche"  ;
	public static final String BLESSE = "touche" ;
	public static final String MORT = "mort" ;
	public static final int NBPERSOS = 3 ;
	public static final int H_PERSO = 44 ;
	public static final int L_PERSO = 39 ;
	public static final String SEPARE = "~" ;
	public static final int PSEUDO = 0 ;
	public static final int H_ARENE = 600 ;
	public static final int L_ARENE = 800 ;
	public static final int H_CHAT = 200 ;
	public static final int H_SAISIE = 25 ;
	public static final int MARGE = 5 ;
	public static final String FONDARENE = CHEMINFONDS +"fondarene.jpg" ;
	public static final int NBMURS = 20 ;
	public static final String CHEMINMURS = CHEMIN + "murs" + SEPARATOR ;
	public static final String MUR = CHEMINMURS + "mur.gif" ;
	public static final int H_MUR = 35 ;
	public static final int L_MUR = 34 ;
	public static final int H_MESSAGE = 8 ; 
	public static final int CHAT = 1 ;
	public static final int  NBETATSMARCHE = 4 ;
	public static final int NBETATSBLESSE = 2 ;
	public static final int NBETATSMORT = 2 ;
	public static final int LEPAS = 10 ;
	public static final int L_BOULE = 17 ;
	public static final int H_BOULE = 17 ;
	public static final String CHEMINBOULES = CHEMIN+"boules"+SEPARATOR  ;
	public static final String BOULE = CHEMINBOULES+"boule.gif" ;
	public static final String CHEMINSONS = CHEMIN + "son/" ;
	public static final String SONPRECEDENT = CHEMINSONS + "precedent.wav" ;
	public static final String SONSUIVANT = CHEMINSONS + "suivant.wav" ;
	public static final String SONGO = CHEMINSONS + "go.wav" ;
	public static final String SONWELCOME = CHEMINSONS + "welcome.wav" ;
	public static final String SONAMBIANCE = CHEMINSONS + "ambiance.wav" ;
	public static final int FIGHT = 0 ;
	public static final int HURT = 1 ;
	public static final int DEATH = 2 ;
	public static final String[] SON = {"fight.wav", "hurt.wav", "death.wav"} ;
	
	}