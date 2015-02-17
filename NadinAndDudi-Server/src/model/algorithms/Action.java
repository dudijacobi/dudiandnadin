package model.algorithms;

import java.io.Serializable;

import model.domain.State;

/**
 * Represents an Action by 'from' and 'to'.
 * 
 * @author Dudi and Nadin
 *
 */
public class Action implements Serializable{
	
	public State from;
	public State to;

}
