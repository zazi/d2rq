package de.fuberlin.wiwiss.d2rq.expr;

import java.util.Set;

import de.fuberlin.wiwiss.d2rq.algebra.AliasMap;
import de.fuberlin.wiwiss.d2rq.algebra.ColumnRenamer;
import de.fuberlin.wiwiss.d2rq.sql.ConnectedDB;

/**
 * An expression that negates an underlying expression
 * 
 * @author Christian Becker <http://beckr.org#chris>
 * @version $Id: Negation.java,v 1.1 2009/08/06 21:50:31 fatorange Exp $
 */
public class Negation extends Expression {
	
	private Expression base;
	
	public Negation(Expression base) {
		this.base = base;
	}

	public Set attributes() {
		return base.attributes();
	}

	public boolean isFalse() {
		return base.isTrue();
	}

	public boolean isTrue() {
		return base.isFalse();
	}

	public Expression renameAttributes(ColumnRenamer columnRenamer) {
		return new Negation(base.renameAttributes(columnRenamer));
	}

	public String toSQL(ConnectedDB database, AliasMap aliases) {
		return "NOT (" + base.toSQL(database, aliases) + ")";
	}
	
	public String toString() {
		return "Negation(" + base + ")";
	}

}