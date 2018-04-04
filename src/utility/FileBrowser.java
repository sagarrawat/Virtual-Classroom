package utility;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class FileBrowser {

    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;


    public FileBrowser(String rootPath) {
    
        File fileRoot = new File(rootPath);
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);        
        createChildren(fileRoot, root);
    }

    public TreeModel getModel(){
        return treeModel;
    }
    
    private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
        
        File[] files = fileRoot.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));
            if (file.isDirectory()) {
                node.add(childNode);
                createChildren(file, childNode);
            }
        }
    }

    public class FileNode {

        private File file;
        private String path;

        public FileNode(File file) {
            this.file = file;
            this.path = file.getAbsolutePath();
        }

        public String getAbsolutePath(){
            return path;
        }
        
        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }

}
