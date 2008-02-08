<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/CurrentWeather">
        <weather-news>
            <temperature><xsl:value-of select="Temperature"/></temperature>
            <humidity><xsl:value-of select="RelativeHumidity"/></humidity>
        </weather-news>
    </xsl:template>
</xsl:stylesheet>