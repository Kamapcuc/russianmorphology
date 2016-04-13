package org.apache.lucene.morphology.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilteringTokenFilter;
import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.util.Version;

import java.io.IOException;

public class WordFormFilter extends FilteringTokenFilter {

    private final LuceneMorphology morphology;
    private final String[] grammemas;

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    public WordFormFilter(TokenStream tokenStream, LuceneMorphology morphology, String[] grammemas) {
        super(Version.LUCENE_46, tokenStream);
        this.morphology = morphology;
        this.grammemas = grammemas;
    }

    @Override
    protected boolean accept() throws IOException {
        String term = termAtt.toString();
        return morphology.checkString(term) && morphology.testWordForm(term, grammemas);
    }

}
