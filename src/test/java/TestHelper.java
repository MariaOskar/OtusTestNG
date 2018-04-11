public class TestHelper {

    public static int random(int min, int max){
        return min + (int)Math.round(Math.random()*(max-min));
    }

    public static String generateZipCode(){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<6;i++){
           sb.append(random(1,9));
        }
        return sb.toString();
    }

    public static String generateCardNum(){
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<4; i++){
            for (int k=0; k<4; k++){
                sb.append(random(0,9));
            }
            if (i!=3) sb.append(" ");
        }
        return sb.toString();
    }

    public static float roundFloat(float n){
        float k = (float) Math.round(n*100);
        return (float)k/100;
    }

    public static void main(String[] args){
    }


}
