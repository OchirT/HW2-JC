import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class CreateFolderFile {

    public static StringBuilder SB = new StringBuilder();
    public static String Save = "C:/Users/User/IdeaProjects/Games/savegames/";
    public static ArrayList<String> SavePath = new ArrayList<>();


    public static File createFolder(String folder) {
        File file = new File(folder);
        if(file.mkdir()) {
            SB.append(file.getName()).append(" - папка успешно создана");
            System.out.println(file.getName() + "- успешно создан");
        } else {
            System.out.println(file.getName() + "- ошибка создания");
        }
        return file;
    }

    public static File createFile(String fileDir) {
        File file = new File(fileDir);
        try {
            if (file.createNewFile()) {
                SB.append(file.getName()).append("- файл успешно создан");
                System.out.println(file.getName() + "- файл успешно создан");
            } else {
                System.out.println(file.getName() + "- ошибка создания");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void LogWriter(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(SB.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveGames(String name, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(Save + name);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            oos.flush();
            SavePath.add(name);
            SB.append(name).append("- сохранение успешно создано");

    } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public static void zipFiles(ArrayList<String> list, String zipName) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(Save + zipName))) {
            for (String s : list) {
                FileInputStream fis = new FileInputStream(Save + s);
                zos.putNextEntry(new ZipEntry(s));
                byte[] buffer = new byte[fis.available()];
                int a;
                if ((a = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, a);
                    SB.append(s).append("файл успешно записан в архив");
                }
                fis.close();
                zos.closeEntry();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openZip(String zipPath, String zipOpenPath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                FileOutputStream fos = new FileOutputStream(zipOpenPath + zipEntry.getName());
                for (int i = zis.read(); i != -1; i = zis.read()) {
                    fos.write(i);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
                SB.append(zipEntry.getName()).append(" успешно распокован");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameProgress openProgress(String saveName) {
        try (FileInputStream fis = new FileInputStream(saveName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFile(String path) {
        try {
            Files.delete(Path.of(path));
            SB.append(path).append("файл успешно удален");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
