package io.gitlab.arturbosch.smellyj

import io.gitlab.arturbosch.smartsmells.api.SmellResult
import io.gitlab.arturbosch.smartsmells.common.Smelly

/**
 * @author artur
 */
object SmellRegistry {

	var smellSet: SmellResult? = null

	fun register(smellResult: SmellResult) {
		smellSet = smellResult
	}

	fun forPath(path: String): List<Smelly> {
		return smellSet?.filter(path) ?: emptyList()
	}

}