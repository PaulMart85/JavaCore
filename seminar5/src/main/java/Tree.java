import java.io.File;

public class Tree {

    /**
     * Распечатывает дерево директорий и файлов относительно текущей директории
     * @param file текущая директория
     * @param indent символ отступа
     * @param isLast последний ли это file в директории
     */
    public static void print(File file, String indent, boolean isLast){
        System.out.print(indent); // рисуем отступ
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;
/*
        int subDirTotal = 0; // для учета только директорий
        for (int i = 0; i < files.length; i++){
           if (files[i].isDirectory())
               subDirTotal++;
        }
*/
//        int subDirCounter = 0, subFlCounter = 0;
//        for (int i = 0; i < files.length; i++) { // создание дерева только директорий
//            if (files[i].isDirectory()){
//                print(files[i], indent, subDirCounter  == subDirTotal - 1);
//                subDirCounter++;
//            }
//        }
        for (int i = 0; i < files.length; i++)
            print(files[i], indent, i == files.length - 1);
    }

}
