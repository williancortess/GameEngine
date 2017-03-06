package com.willian.cortes.simplegameenginev1;

/**
 * Created by Willian on 04/03/2017.
 *
 * Recebe uma string e transforma em uma sequencia de indices
 */

public class SGText {
    private String mText;
    private char[] mCharacters;

    public SGText(String text)
    {
        mText = text;
        _buildCharArray(text);
    }

    public char[]		getCharacters() { return mCharacters; }
    public int			getLength() { return mCharacters.length; }
    public String 		getString() { return mText; }

    public void setString(String text)
    {
        mText = text;
        _buildCharArray(text);
    }

    private void _buildCharArray(String text)
    {
        mCharacters = new char[text.length()];

        for(int i = 0; i < text.length(); i++)
        {
            mCharacters[i] = text.charAt(i);
        }
    }
}
