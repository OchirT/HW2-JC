public class Main  extends CreateFolderFile {
    public static void main(String[] args) {

        String gameFolder = "C:/Users/User/IdeaProjects/Games/";
        createFolder(gameFolder + "src");
        createFolder(gameFolder + "res");
        createFolder(gameFolder + "savegames");
        createFolder(gameFolder + "temp");
        createFolder(gameFolder + "src/main");
        createFolder(gameFolder + "src/test");
        createFolder(gameFolder + "res/drawables");
        createFolder(gameFolder + "res/vectors");
        createFolder(gameFolder + "res/icons");
        createFile(gameFolder + "src/main/Main.java");
        createFile(gameFolder + "src/main/Utils.java");
        createFile(gameFolder + "temp/temp.txt");
        LogWriter(gameFolder + "temp/temp.txt");

        GameProgress gameProgress = new GameProgress(100, "Пулемет", 55, 250);
        GameProgress gameProgress1 = new GameProgress(95, "Автомат", 60, 150);
        GameProgress gameProgress2 = new GameProgress(80, "Лук", 65, 50);

        saveGames("save.dat", gameProgress);
        saveGames("save2.dat", gameProgress1);
        saveGames("save3.dat", gameProgress2);
        zipFiles(SavePath, "allSaves");
        for (String file:SavePath) {
            deleteFile(Save + file);
        }
        openZip(Save + "allSaves.zip", Save);
        System.out.println(openProgress(Save + "save.dat"));
        LogWriter(gameFolder + "temp/temp.txt");




    }
}
