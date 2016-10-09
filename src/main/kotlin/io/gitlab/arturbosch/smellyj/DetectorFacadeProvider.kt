package io.gitlab.arturbosch.smellyj

import io.gitlab.arturbosch.smartsmells.api.DetectorFacade
import io.gitlab.arturbosch.smartsmells.config.DetectorConfig
import java.nio.file.Files

/**
 * @author Artur Bosch
 */
object DetectorFacadeProvider {

	fun get(): DetectorFacade {
		val configPath = ConfigurationProvider.detectorConfig()
		return if (Files.exists(configPath)) {
			DetectorFacade.fromConfig(DetectorConfig.load(configPath))
		} else {
			DetectorFacade.fullStackFacade()
		}
	}

}