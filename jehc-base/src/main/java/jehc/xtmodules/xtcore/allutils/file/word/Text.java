package jehc.xtmodules.xtcore.allutils.file.word;

public abstract class Text {

    public abstract String getText();

    public static Text str(final String string) {
        return new Text() {
            @Override
            public String getText() {
                return string;
            }
        };
    }

}
