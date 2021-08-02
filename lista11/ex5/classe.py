class FormattingOptions():
    def __init__(self, format):
        """Construtor."""
        self.format = format
        self.format_preffix = ""

    def set(self, format):
        """Colocar o formato do texto."""
        self.format = format
        
    def show(self):
        """Mostra o formato."""
        if self.format is None:
            self.format = " "
        str_show = self.format_preffix
        if str_show != "":
            str_show += " "
        str_show += self.format
        return str_show

class Color(FormattingOptions):
    def __init__(self, format):
        """Construtor."""
        super().__init__(format)
        self.format_preffix = "na cor"

    def set(self, cor):
        """Determina qual será a cor."""
        super().set(cor)
    
class Style(FormattingOptions):
    def __init__(self, format):
        """Construtor."""
        super().__init__(format)

    def set(self, style):
        """Determina qual será o estilo.

        Opções:
        - 1 para sublinhado
        - 2 para itálico
        - 3 para sublinhado e itálico
        - 4 para negrito
        - 5 para negrito e sublinhado
        - 6 para negrito e itálico
        - 7 para negrito, itálico e sublinhado
        """
        if style == 1:
            super().set("sublinhado")
        elif style == 2:
            super().set("itálico")
        elif style == 3:
            super().set("sublinhado e itálico")
        elif style == 4:
            super().set("negrito")
        elif style == 5:
            super().set("negrito e sublinhado")
        elif style == 6:
            super().set("negrito e itálico")
        elif style == 7:
            super().set("negrito, itálico e sublinhado")

class Font(FormattingOptions):
    def __init__(self, format):
        """Construtor."""
        super().__init__(format)
        self.format_preffix = "com fonte"

    def set(self, font):
        """Coloca a fonte."""
        super().set(font)

class Text():
    def __init__(self, content):
        """Construtor."""
        self.content = content

    def getContent(self):
        """Retorna a string com o conteúdo."""
        return self.content

    def edit(self, new_content):
        """Altera o conteúdo do texto."""
        self.content = new_content

    def append(self, content):
        """Adiciona um novo texto ao que já tinha."""
        self.content += content

class FormattedText(Text):
    def __init__(self, content):
        """Construtor."""
        super().__init__(content)
        self.font = Font("")
        self.color = Color("")
        self.style = Style("")

    def show(self):
        """Mostra o conteúdo formatado."""
        fmt = ""
        if self.style.show() != "":
            print(self.style.show())
            if fmt != "":
                fmt += ", "
            fmt += self.style.show()
        if self.font.show() != "":
            print(self.font.show())
            if fmt != "":
                fmt += ", "
            fmt += self.font.show()
        if self.color.show() != "":
            print(self.color.show())
            if fmt != "":
                fmt += ", "
            fmt += self.color.show()
        if fmt == "":
            fmt = "sem formatação"
            
        text_format = '\"' + self.getContent() + " -> " + fmt + '\"'
        
        return text_format

    def setFont(self, font):
        """Escolhe a fonte."""
        self.font.set(font)
        
    def setColor(self, color):
        """Escolhe a cor da fonte."""
        self.color.set(color)
        
    def setStyle(self, style):
        """Escolhe o estilo da fonte."""
        self.style.set(style)