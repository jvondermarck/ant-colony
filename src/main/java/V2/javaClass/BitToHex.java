package V2.javaClass;

import java.util.BitSet;

public class BitToHex {
    public static String hex(BitSet grid)
    {
//        int hex = 0;
//        for (int i =0; i<grid.size() ;i++)
//        {
//            if(grid.get(i))
//            {
//                hex += 1<<i;
//            }
//        }
        return String.format("%s", grid.toString());
    }

}
