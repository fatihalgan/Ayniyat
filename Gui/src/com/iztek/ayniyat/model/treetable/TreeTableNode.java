package com.iztek.ayniyat.model.treetable;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Extends DefaultMutableTreeNode to support sorting of the tree.
 * 
 * @author Vincent Vollers
 */
public class TreeTableNode extends DefaultMutableTreeNode implements TreeNode{
	private boolean selected = false;
	
	/**
	 * Creates a tree node that has no parent and no children, but which allows
	 * children.
	 */
	public TreeTableNode() {
		super(null);
	}

	/**
	 * Creates a tree node with no parent, no children, but which allows
	 * children, and initializes it with the specified user object.
	 * 
	 * @param userObject
	 *            an Object provided by the user that constitutes the node's
	 *            data
	 */
	public TreeTableNode(Object userObject) {
		super(userObject);
	}

	/**
	 * Creates a tree node with no parent, no children, initialized with the
	 * specified user object, and that allows children only if specified.
	 * 
	 * @param userObject
	 *            an Object provided by the user that constitutes the node's
	 *            data
	 * @param allowsChildren
	 *            if true, the node is allowed to have child nodes --
	 *            otherwise, it is always a leaf node
	 */
	public TreeTableNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}

	/***
	 * Sorts the children of the node and recurses through them as specified.
	 * @param sorter the Comparator to use to sort the nodes
	 * @param recursive when true, recurse through the entire tree
	 */
	public void sort(Comparator sorter, boolean recursive) {
		if(children == null) {
			return;
		}
		if(recursive) {
			Iterator i = children.iterator();
			while(i.hasNext()) {
				TreeTableNode child = (TreeTableNode) i.next();
				child.sort(sorter, recursive);
			}
		}
		Collections.sort(children, sorter);
	}
	
	/***
	 * Recursively sort this node and all its children using the specified Comparator.
	 * @param sorter the Comparator to use to sort the nodes
	 */
	public void sort(Comparator sorter) {
		sort(sorter, true);
	}
	
	/***
	 * Gives the index of given child of this node.
	 * @param child the child-node to look for
	 * @return the index of the child in this node
	 */
	public int indexOf(Object child) {
		if(children == null) {
			return -1;
		}
		return children.indexOf(child);
	}
	
	/***
	 * Returns the toString of the userObject, unless userObject is null
	 */
	public String toString() {
		if(userObject == null) {
			return "";
		} else {
			return userObject.toString();
		}
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Vector getChildren(){
		return children;
	}
}
