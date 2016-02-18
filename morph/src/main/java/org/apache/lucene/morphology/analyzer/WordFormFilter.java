package org.apache.lucene.morphology.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilteringTokenFilter;
import org.apache.lucene.morphology.Morphology;
import org.apache.lucene.util.Version;

import java.io.IOException;

public class WordFormFilter extends FilteringTokenFilter {

    private final Morphology morphology;
    private final String[] grammemas;

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    public WordFormFilter(TokenStream tokenStream, Morphology morphology, String[] grammemas) {
        super(Version.LUCENE_46, tokenStream);
        this.morphology = morphology;
        this.grammemas = grammemas;
    }

    @Override
    protected boolean accept() throws IOException {
        return morphology.testWordForm(termAtt.toString(), grammemas);
    }
}
