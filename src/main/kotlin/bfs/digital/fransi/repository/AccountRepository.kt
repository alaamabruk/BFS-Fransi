package bfs.digital.fransi.repository

import bfs.digital.fransi.entity.AccountDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountDetails, Long>