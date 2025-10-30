package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.utils.JwtUtils;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证Controller
 */
@Tag(name = "用户认证", description = "用户登录、注册、获取用户信息等认证相关接口")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174", "http://localhost:8000"})
public class AuthController {
    
    private final SysUserService sysUserService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * 用户登录
     */
    @Operation(summary = "用户登录", description = "通过邮箱和密码进行用户登录，返回JWT令牌和用户信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登录成功",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = AjaxResult.class),
                examples = @ExampleObject(value = "{\"code\": 200, \"message\": \"登录成功\", \"data\": {\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\", \"user\": {\"userId\": 1, \"userUsername\": \"admin\", \"userEmail\": \"admin@example.com\"}}}"))),
        @ApiResponse(responseCode = "400", description = "邮箱或密码错误",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"code\": 400, \"message\": \"邮箱或密码错误\"}"))),
        @ApiResponse(responseCode = "403", description = "账户已被停用", 
            content = @Content(mediaType = "application/json", 
                examples = @ExampleObject(value = "{\"code\": 403, \"message\": \"账户已被停用\"}")))
    })
    @PostMapping("/login")
    public AjaxResult login(
        @Parameter(description = "登录请求信息", required = true)
        @RequestBody LoginRequest request) {
        // 验证用户 - 使用邮箱登录
        SysUser user = sysUserService.getByEmail(request.getUsername());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
            return AjaxResult.error("邮箱或密码错误");
        }
        
        if (user.getUserStatus() != null && user.getUserStatus() == 0) {
            return AjaxResult.error("账户已被停用");
        }
        
        // 生成JWT令牌
        String token = jwtUtils.generateToken(user.getUserName(), user.getUserRoleId().toString());
        
        // 返回用户信息和令牌
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);
        
        return AjaxResult.success("登录成功", data);
    }
    
    /**
     * 用户注册
     */
    @Operation(summary = "用户注册", description = "新用户注册，创建用户账户")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "注册成功", 
            content = @Content(mediaType = "application/json", 
                examples = @ExampleObject(value = "{\"code\": 200, \"message\": \"注册成功\"}"))),
        @ApiResponse(responseCode = "400", description = "用户名已存在", 
            content = @Content(mediaType = "application/json", 
                examples = @ExampleObject(value = "{\"code\": 400, \"message\": \"用户名已存在\"}"))),
        @ApiResponse(responseCode = "400", description = "邮箱已被注册", 
            content = @Content(mediaType = "application/json", 
                examples = @ExampleObject(value = "{\"code\": 400, \"message\": \"邮箱已被注册\"}")))
    })
    @PostMapping("/register")
    public AjaxResult register(
        @Parameter(description = "注册请求信息", required = true)
        @Valid @RequestBody RegisterRequest request) {
        // 检查用户名是否已存在
        if (sysUserService.getByUsername(request.getUsername()) != null) {
            return AjaxResult.error("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (sysUserService.getByEmail(request.getEmail()) != null) {
            return AjaxResult.error("邮箱已被注册");
        }
        
        // 创建新用户
        SysUser user = new SysUser();
        user.setUserName(request.getUsername());
        user.setUserEmail(request.getEmail());
        user.setUserPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserRealName(request.getRealName());
        user.setUserAffiliation(request.getAffiliation());
        user.setUserPhone(request.getPhone());
        user.setUserDepartment(request.getDepartment());
        user.setUserTitle(request.getTitle());
        user.setUserPosition(request.getPosition());
        user.setUserOrcid(request.getOrcid());
        user.setUserCountry(request.getCountry());
        user.setUserState(request.getState());
        user.setUserCity(request.getCity());
        user.setUserPostalCode(request.getPostalCode());
        user.setUserAddress(request.getAddress());
        user.setUserClassification(request.getClassification());
        user.setUserKeywords(request.getKeywords());
        user.setUserBiography(request.getBiography());
        user.setUserRoleId(2L); // 默认为作者角色
        user.setUserStatus(1); // 正常状态
        user.setUserEmailVerified(0); // 邮箱未验证
        
        boolean success = sysUserService.save(user);
        return success ? AjaxResult.success("Registration successful") : AjaxResult.error("Registration failed");
    }
    
    /**
     * 获取当前用户信息
     */
    @Operation(summary = "获取当前用户信息", description = "通过JWT令牌获取当前登录用户的详细信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = AjaxResult.class),
                examples = @ExampleObject(value = "{\"code\": 200, \"message\": \"success\", \"data\": {\"userId\": 1, \"userUsername\": \"admin\", \"userEmail\": \"admin@example.com\"}}"))),
        @ApiResponse(responseCode = "401", description = "获取用户信息失败", 
            content = @Content(mediaType = "application/json", 
                examples = @ExampleObject(value = "{\"code\": 401, \"message\": \"获取用户信息失败\"}")))
    })
    @GetMapping("/me")
    public AjaxResult getCurrentUser(
        @Parameter(description = "JWT令牌", required = true, example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        @RequestHeader("Authorization") String token) {
        try {
            String username = jwtUtils.getUsernameFromToken(token.substring(7));
            SysUser user = sysUserService.getByUsername(username);
            if (user != null) {
                // 清除密码信息
                user.setUserPassword(null);
            }
            return AjaxResult.success(user);
        } catch (Exception e) {
            return AjaxResult.error("获取用户信息失败");
        }
    }
    
    /**
     * 登录请求类
     */
    @Schema(description = "用户登录请求")
    public static class LoginRequest {
        @Schema(description = "邮箱地址", example = "admin@example.com", required = true)
        private String username;
        
        @Schema(description = "密码", example = "password123", required = true)
        private String password;
        
        // getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    /**
     * 注册请求类
     */
    @Schema(description = "用户注册请求")
    public static class RegisterRequest {
        @Schema(description = "用户名", example = "john_doe", required = true)
        private String username;
        
        @Schema(description = "邮箱地址", example = "john@example.com", required = true)
        private String email;
        
        @Schema(description = "密码", example = "password123", required = true)
        private String password;
        
        @Schema(description = "真实姓名", example = "John Doe", required = true)
        private String realName;
        
        @Schema(description = "所属机构", example = "Harvard University", required = true)
        private String affiliation;
        
        @Schema(description = "电话号码", example = "+1 234 567 8900")
        private String phone;
        
        @Schema(description = "学院/研究所", example = "Computer Science Department")
        private String department;
        
        @Schema(description = "职称", example = "Professor")
        private String title;
        
        @Schema(description = "职位", example = "Department Chair")
        private String position;
        
        @Schema(description = "ORCID", example = "0000-0000-0000-0000")
        private String orcid;
        
        @Schema(description = "国家/地区", example = "United States", required = true)
        private String country;
        
        @Schema(description = "州/省", example = "California")
        private String state;
        
        @Schema(description = "城市", example = "San Francisco")
        private String city;
        
        @Schema(description = "邮政编码", example = "94102")
        private String postalCode;
        
        @Schema(description = "详细地址")
        private String address;
        
        @Schema(description = "研究领域", example = "Machine Learning, Computer Vision", required = true)
        private String classification;
        
        @Schema(description = "研究关键词", example = "Deep Learning, AI", required = true)
        private String keywords;
        
        @Schema(description = "个人简介", required = true)
        private String biography;
        
        // getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRealName() { return realName; }
        public void setRealName(String realName) { this.realName = realName; }
        public String getAffiliation() { return affiliation; }
        public void setAffiliation(String affiliation) { this.affiliation = affiliation; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }
        public String getOrcid() { return orcid; }
        public void setOrcid(String orcid) { this.orcid = orcid; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getPostalCode() { return postalCode; }
        public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getClassification() { return classification; }
        public void setClassification(String classification) { this.classification = classification; }
        public String getKeywords() { return keywords; }
        public void setKeywords(String keywords) { this.keywords = keywords; }
        public String getBiography() { return biography; }
        public void setBiography(String biography) { this.biography = biography; }
    }
}

