package e.nick.kryptonote;


public class Cipher
{
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789#%";
    private String key;

    public Cipher(String k)
    {
        this.key = k;
    }

    private String makePad(String note)
    {
        String pad = this.key;

        while(pad.length() < note.length())
        {
            pad += this.key;
        }

        return pad;
    }

    public String encrypt(String note)
    {
        String pad = makePad(note);
        String result = "";

        for(int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = pad.charAt(i);
            int newPosition = (position + shift) % ALPHABET.length();
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public String decrypt(String note)
    {
        String pad = makePad(note);
        String result = "";

        for(int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = pad.charAt(i);
            int newPosition = position - shift;
            while (newPosition < 0){
                newPosition += ALPHABET.length();
            }
            newPosition %= ALPHABET.length();
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }
}
