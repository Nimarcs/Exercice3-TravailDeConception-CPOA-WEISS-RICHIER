// PACKAGE
package tests;

// IMPORTS
import code.Etudiant;
import code.Formation;
import code.Identite;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * TEST CONSTRUCTEUR
 */
public class ConstructeurTest {

    @Test
    public void testConstructeurEtudiant_CasOK() {
        Etudiant e = new Etudiant(new Identite("0001", "Weiss", "Lucas"), new Formation("DUT INFO"));
    }
    @Test (expected = InvalidParameterException.class)
    public void testConstructeurEtudiant_CasNullIdentite() {
        Etudiant e = new Etudiant(null, new Formation("DUT INFO"));
    }
    @Test (expected = InvalidParameterException.class)
    public void testConstructeurEtudiant_CasNullFormation() {
        Etudiant e = new Etudiant(new Identite("0001", "Weiss", "Lucas"), null);
    }

}