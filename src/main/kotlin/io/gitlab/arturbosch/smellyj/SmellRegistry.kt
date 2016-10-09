package io.gitlab.arturbosch.smellyj

import io.gitlab.arturbosch.smartsmells.api.SmellResult
import io.gitlab.arturbosch.smartsmells.common.DetectionResult

/**
 * @author Artur Bosch
 */
object SmellRegistry {

	var smellSet: SmellResult? = null

	fun register(smellResult: SmellResult) {
		smellSet = smellResult
	}

	fun forPath(path: String): List<DetectionResult> {
		return smellSet?.filter(path) ?: emptyList()
	}

}