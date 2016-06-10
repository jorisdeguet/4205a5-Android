package deguet.org.babytracker.repo;

import android.content.Context;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.deguet.model.Identifiable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by joris on 15-09-15.
 */
public class RepoGSON<T extends Identifiable> {

    private final Gson gson = new Gson();
    private final Class<T> classe;
    private final File base;
    private final String extension;
    private final Context context;

    public RepoGSON(Context c, Class<T> classs){
        this.context = c;
        this.classe = classs;
        this.base = new File(context.getFilesDir(),this.classe.getSimpleName());
        this.extension = "."+this.classe.getSimpleName();
        this.createStorage();
    }

    public List<T> getAll() {
        synchronized (classe) {
            List<T> res = new ArrayList<T>();
            File base = context.getFilesDir();
            for (File f : base.listFiles()){
                if (f.getName().contains(extension)) {
                    try {
                        //System.out.println("File is "+f.getName());
                        String content = FileUtils.readFileToString(f);
                        T a = gson.fromJson(content, classe);
                        res.add(a);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return res;
        }
    }

    public void deleteOne(UUID id) {
        this.deleteOne(this.getById(id));
    }

    public String save(T a) {
        synchronized (classe) {
            // set the id
            if (a.getId() == null) a.setId(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            File base = context.getFilesDir();
            try {
                FileUtils.writeStringToFile(new File(base, a.getId()+extension), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getId();

        }
    }

    public void saveMany(Iterable<T> list) {
        for (T p : list ){
            this.save(p);
        }
    }

    public void saveMany(T... list) {
        for (T p : list ){
            this.save(p);
        }
    }

    public T getById(UUID id) {
        synchronized (classe) {
            String content;
            try {
                File base = context.getFilesDir();
                File f = new File(base,id+extension);
                if (!f.exists()) return null;
                content = FileUtils.readFileToString(f);
                T a = gson.fromJson(content, classe);
                return a;
            } catch (IOException e) {
                return null;
            }
        }
    }

    public void deleteOne(T a) {
        synchronized (classe) {
            File base = context.getFilesDir();
            File f = new File(base, a.getId()+extension);
            f.delete();
        }
    }

    public void deleteAll() {
        deleteStorage();
        createStorage();
    }

    // autre methodes hors acces aux donnees pour la gestion.

    private String nextAvailableId(){
        return UUID.randomUUID().toString();
    }

    private void deleteStorage(){
        File base = context.getFilesDir();
        deleteFolder(base);
    }

    private void createStorage(){
        File base = context.getFilesDir();
        base.mkdirs();
    }

    private static void deleteFolder(File folder) {
        try{File[] files = folder.listFiles();
            if(files!=null) {
                for(File f: files) {
                    if(f.isDirectory())
                        deleteFolder(f);
                    else
                        f.delete();
                }
            }
            folder.delete();
        }
        catch(Exception e){}
    }

}
