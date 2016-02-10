package failsafe;

import junit.framework.TestCase;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppIT extends TestCase
{
    public void testExecute()
        throws Exception
    {
        assertEquals( 0, execute( new String[] {} ) );
        assertEquals( 1, execute( new String[] { "one" } ) );
        assertEquals( 6, execute( new String[] { "one", "two", "three", "four", "five", "six" } ) );
    }

    private int execute( String[] args ) throws Exception
    {
        File jar = new File( "target/failsafe-1.0-SNAPSHOT.jar" );

        String[] execArgs = new String[args.length + 3];
        System.arraycopy( args, 0, execArgs, 3, args.length );
        execArgs[0] = "java";
        execArgs[1] = "-jar";
        execArgs[2] = jar.getCanonicalPath();
        Process p = Runtime.getRuntime().exec( execArgs );
        p.waitFor();
        return p.exitValue();
    }

}
