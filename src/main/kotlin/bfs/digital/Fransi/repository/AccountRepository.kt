package bfs.digital.Fransi.repository

import bfs.digital.Fransi.entity.AccountDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountDetails, Long>